package org.provoysa12th.directory.domain;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.provoysa12th.directory.domain.exception.InvalidMRNFormatException;

/**
 * A SHA-1 hash of the LDS Membership Record Number (MRN). See: {@link InvalidMRNFormatException}.
 * <p>
 * See: {@link http://tech.lds.org/wiki/Membership_record_number}
 * </p>
 * @author FransonSR
 *
 */
public class MRNHash {

	public static final String MRN_PATTERN = "[0-9]{3}-[0-9]{4}-[0-9]{3}[0-9A]";

	private static final String ALGORITHM = "SHA-1";
	private static final String SEED = "Alma 37:14-18";

	private final String hash;

	public MRNHash(String mrn) {

		if(!mrn.matches(MRN_PATTERN)) {
			throw new InvalidMRNFormatException("Invalid MRN format: " + mrn);
		}

		String firstHash = generate(SEED + ":" + mrn);

		hash = generate(mrn + ":" + firstHash);
	}

	private String generate(String value) {
		CharBuffer charBuffer = CharBuffer.wrap(value);
		ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
		byte[] bytes = byteBuffer.array();

		byte[] hashBytes = create(bytes);

        StringBuffer hashBuffer = new StringBuffer();
        for(byte b : hashBytes) {
            hashBuffer.append(Integer.toHexString(0xFF & b));
        }

        return hashBuffer.toString();
	}

    private byte[] create(byte[] data) {
        byte[] hashBytes = new byte[0];

        try {
            hashBytes = MessageDigest.getInstance(ALGORITHM).digest(data);
        } catch (NoSuchAlgorithmException e1) {
        }

        return hashBytes;
    }

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(hash)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MRNHash other = (MRNHash) obj;
		return new EqualsBuilder()
			.append(hash, other.hash)
			.isEquals();
	}

	@Override
	public String toString() {
		return hash;
	}
}
