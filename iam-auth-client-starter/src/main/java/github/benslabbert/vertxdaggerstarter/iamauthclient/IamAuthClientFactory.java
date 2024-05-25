/* Licensed under Apache-2.0 2024. */
package github.benslabbert.vertxdaggerstarter.iamauthclient;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface IamAuthClientFactory {

  IamAuthClient create(String baseUrl, int port);
}
