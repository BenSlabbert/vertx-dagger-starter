/* Licensed under Apache-2.0 2024. */
package github.benslabbert.vertxdaggerstarter.jdbcpool;

import dagger.Module;
import javax.sql.DataSource;

@Module(includes = {BlockingJdbcPoolConfig.class, ModuleBindings.class})
public interface JdbcPoolModule {

  DataSource dataSource();
}
