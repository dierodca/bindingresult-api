
package co.com.carvajal.platform.modules.orders.dataproviders.jpa;

import java.util.List;
import co.com.carvajal.foundation.framework.stereotypes.DataProvider;
import co.com.carvajal.platform.crosscutting.domain.DocumentStorageData;
import co.com.carvajal.platform.crosscutting.patterns.builders.RestTemplateBuilder;
import co.com.carvajal.platform.modules.orders.dataproviders.OrdersDataProvider;
import lombok.extern.log4j.Log4j2;

/**
 * JpaOrderDataProvider
 *
 * @author Carvajal
 * @version 1.0
 * @since 2020-04-22
 *
 */

@Log4j2
@DataProvider
public class JpaOrderDataProvider implements OrdersDataProvider {

    private static final String URL_DS = "http://10.75.32.71:4680/rest/ds/document/metadata/";

    @Override
    public void process(final List<String> files) {
        log.error("Procesando...");

        files.forEach(documentstorageid -> {
            try {
                final DocumentStorageData response = RestTemplateBuilder.builder()
                                .withUrl(URL_DS.concat(documentstorageid))
                                .buildBasicGET(DocumentStorageData.class);
                log.debug("DOCUMENTO ENCONTRADO " + response);
            } catch (final Exception e) {
                log.error("ERROR: " + e.getMessage() + " DocumentStorageId Error: "
                                + documentstorageid);
            }

        });
    }
}
