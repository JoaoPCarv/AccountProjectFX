import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.junit.runners.BlockJUnit4ClassRunner;
import service.propertiesService.HibernateProperties;

import java.util.Properties;

@RunWith(BlockJUnit4ClassRunner.class)
public final class JDBCTest {

    @Deprecated
    private JDBCTest(){}

    private static Properties hbProps;

    @Before
    private static void loadHibernateProperties(){
        hbProps = HibernateProperties.getHibernateProperties();
    }

    @Test
    public static void DriverClass_shouldBe_RegisteredProperly() throws ClassNotFoundException{
        String driver = hbProps.getProperty("javax.persistence.jdbc.driver");
        assertEquals(Class.forName(driver), com.microsoft.sqlserver.jdbc.SQLServerDriver.class);

    }

}
