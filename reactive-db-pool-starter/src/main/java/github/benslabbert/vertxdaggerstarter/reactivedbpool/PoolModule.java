/* Licensed under Apache-2.0 2023. */
package github.benslabbert.vertxdaggerstarter.reactivedbpool;

import dagger.Module;
import io.vertx.sqlclient.Pool;

@Module(includes = {PoolConfig.class, ModuleBindings.class})
public interface PoolModule {

  Pool pool();
}
