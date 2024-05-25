/* Licensed under Apache-2.0 2023. */
package github.benslabbert.vertxdaggerstarter.redis;

import dagger.Module;
import io.vertx.redis.client.RedisAPI;

@Module(includes = {RedisAPIProvider.class, ModuleBindings.class})
public interface RedisModule {

  RedisAPI redisAPI();
}
