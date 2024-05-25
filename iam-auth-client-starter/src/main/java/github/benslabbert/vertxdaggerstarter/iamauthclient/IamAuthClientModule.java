/* Licensed under Apache-2.0 2024. */
package github.benslabbert.vertxdaggerstarter.iamauthclient;

import dagger.Module;

@Module
public interface IamAuthClientModule {

  IamAuthClientFactory iamAuthClientFactory();
}
