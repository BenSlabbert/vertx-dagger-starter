/* Licensed under Apache-2.0 2024. */
package github.benslabbert.vertxdaggerstarter.iamauthclient;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import github.benslabbert.vertxdaggerapp.api.iam.auth.IamAuthApi;
import github.benslabbert.vertxdaggerapp.api.iam.auth.dto.LoginRequestDto;
import github.benslabbert.vertxdaggerapp.api.iam.auth.dto.LoginResponseDto;
import github.benslabbert.vertxdaggerapp.api.iam.auth.dto.RefreshRequestDto;
import github.benslabbert.vertxdaggerapp.api.iam.auth.dto.RefreshResponseDto;
import github.benslabbert.vertxdaggerapp.api.iam.auth.dto.RegisterRequestDto;
import github.benslabbert.vertxdaggerapp.api.iam.auth.dto.RegisterResponseDto;
import github.benslabbert.vertxdaggerapp.api.iam.auth.dto.UpdatePermissionsRequestDto;
import github.benslabbert.vertxdaggerapp.api.iam.auth.dto.UpdatePermissionsResponseDto;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpStatusClass;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.handler.HttpException;
import java.util.function.Function;

public final class IamAuthClient implements IamAuthApi {

  private final WebClient webClient;

  @AssistedInject
  IamAuthClient(Vertx vertx, @Assisted String baseUrl, @Assisted int port) {
    this.webClient =
        WebClient.create(
            vertx, new WebClientOptions().setDefaultHost(baseUrl).setDefaultPort(port));
  }

  @Override
  public Future<LoginResponseDto> login(LoginRequestDto req) {
    return webClient
        .post("/api/login")
        .putHeader(CONTENT_TYPE.toString(), APPLICATION_JSON.toString())
        .sendJson(req.toJson())
        .compose(resp -> handleResponse(resp, LoginResponseDto::fromJson));
  }

  @Override
  public Future<RefreshResponseDto> refresh(RefreshRequestDto req) {
    return webClient
        .post("/api/refresh")
        .putHeader(CONTENT_TYPE.toString(), APPLICATION_JSON.toString())
        .sendJson(req.toJson())
        .compose(resp -> handleResponse(resp, RefreshResponseDto::fromJson));
  }

  @Override
  public Future<RegisterResponseDto> register(RegisterRequestDto req) {
    return webClient
        .post("/api/register")
        .putHeader(CONTENT_TYPE.toString(), APPLICATION_JSON.toString())
        .sendJson(req.toJson())
        .compose(resp -> handleResponse(resp, RegisterResponseDto::fromJson));
  }

  @Override
  public Future<UpdatePermissionsResponseDto> updatePermissions(UpdatePermissionsRequestDto req) {
    return webClient
        .post("/api/update-permissions")
        .putHeader(CONTENT_TYPE.toString(), APPLICATION_JSON.toString())
        .sendJson(req.toJson())
        .compose(resp -> handleResponse(resp, UpdatePermissionsResponseDto::fromJson));
  }

  private <T> Future<T> handleResponse(
      HttpResponse<Buffer> resp, Function<JsonObject, T> function) {
    var status = HttpResponseStatus.valueOf(resp.statusCode());
    if (status.codeClass() == HttpStatusClass.SUCCESS) {
      return Future.succeededFuture(function.apply(resp.bodyAsJsonObject()));
    } else {
      return Future.failedFuture(new HttpException(resp.statusCode()));
    }
  }
}
