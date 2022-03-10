package co.com.carvajal.platform.crosscutting.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo para obtener los datos del documento desde el endpoint metadata
 *
 * @author carvajal
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentStorageData implements Serializable {

    private static final long serialVersionUID = 6063531066232838024L;

    private String id;
    private String properties;
    private String metadata;
    private String tags;
    private String prefix;
    private byte[] content;
}
