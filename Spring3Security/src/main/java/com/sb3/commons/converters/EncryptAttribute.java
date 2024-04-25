package com.sb3.commons.converters;

import com.sb3.commons.constants.EncryptionConstants;
import com.sb3.utils.EncryptionUtils;
import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncryptAttribute implements AttributeConverter<String, String> {

    /**
     * Use following annotation on entity fields to apply EncryptAttribute
     * @Convert(converter = EncryptAttribute.class)
     *
     * **/

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return EncryptionUtils.encrypt(attribute, EncryptionConstants.ENCRYPTION_ALGO_AES_SECRET_KEY, EncryptionConstants.ENCRYPTION_ALGO_AES_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return EncryptionUtils.decrypt(dbData, EncryptionConstants.ENCRYPTION_ALGO_AES_SECRET_KEY, EncryptionConstants.ENCRYPTION_ALGO_AES_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
