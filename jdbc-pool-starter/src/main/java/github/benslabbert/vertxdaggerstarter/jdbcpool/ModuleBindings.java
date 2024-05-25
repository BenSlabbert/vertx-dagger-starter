/* Licensed under Apache-2.0 2024. */
package github.benslabbert.vertxdaggerstarter.jdbcpool;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
interface ModuleBindings {

  @Binds
  @IntoSet
  AutoCloseable asAutoCloseable(BlockingJdbcPoolConfig closeablePool);
}
