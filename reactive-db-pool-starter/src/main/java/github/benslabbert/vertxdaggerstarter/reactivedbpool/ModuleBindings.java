/* Licensed under Apache-2.0 2023. */
package github.benslabbert.vertxdaggerstarter.reactivedbpool;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
interface ModuleBindings {

  @Binds
  @IntoSet
  AutoCloseable asAutoCloseable(PoolConfig poolConfig);
}
