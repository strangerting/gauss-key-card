package io.github.darconeous.gausskeycard;

import javacard.security.ECKey;
import javacard.security.ECPrivateKey;
import javacard.security.ECPublicKey;
import javacard.security.KeyBuilder;
import javacard.security.KeyPair;

/** Class for creating NIST P-256 keys. */
public class ECP256 {
	public static final short PRIVATE_KEY_LENGTH = 256;

	public static final byte[] nistp256_p = {
	    (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x0,
	    (byte)0x0, (byte)0x0, (byte)0x1, (byte)0x0, (byte)0x0, (byte)0x0,
	    (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0,
	    (byte)0x0, (byte)0x0, (byte)0x0, (byte)0xff, (byte)0xff, (byte)0xff,
	    (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
	    (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff
	};

	public static final byte[] nistp256_a = {
	    (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x0,
	    (byte)0x0, (byte)0x0, (byte)0x1, (byte)0x0, (byte)0x0, (byte)0x0,
	    (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0,
	    (byte)0x0, (byte)0x0, (byte)0x0, (byte)0xff, (byte)0xff, (byte)0xff,
	    (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
	    (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xfc
	};

	public static final byte[] nistp256_b = {
	    (byte)0x5a, (byte)0xc6, (byte)0x35, (byte)0xd8, (byte)0xaa,
	    (byte)0x3a, (byte)0x93, (byte)0xe7, (byte)0xb3, (byte)0xeb,
	    (byte)0xbd, (byte)0x55, (byte)0x76, (byte)0x98, (byte)0x86,
	    (byte)0xbc, (byte)0x65, (byte)0x1d, (byte)0x6, (byte)0xb0,
	    (byte)0xcc, (byte)0x53, (byte)0xb0, (byte)0xf6, (byte)0x3b,
	    (byte)0xce, (byte)0x3c, (byte)0x3e, (byte)0x27, (byte)0xd2,
	    (byte)0x60, (byte)0x4b
	};

	public static final byte[] nistp256_R = {
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0x00,
	    (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	    (byte)0xFF, (byte)0xBC, (byte)0xE6, (byte)0xFA, (byte)0xAD,
	    (byte)0xA7, (byte)0x17, (byte)0x9E, (byte)0x84, (byte)0xF3,
	    (byte)0xB9, (byte)0xCA, (byte)0xC2, (byte)0xFC, (byte)0x63,
	    (byte)0x25, (byte)0x51
	};

	public static final byte[] nistp256_G = {
	    (byte)0x4, (byte)0x6b, (byte)0x17, (byte)0xd1, (byte)0xf2,
	    (byte)0xe1, (byte)0x2c, (byte)0x42, (byte)0x47, (byte)0xf8,
	    (byte)0xbc, (byte)0xe6, (byte)0xe5, (byte)0x63, (byte)0xa4,
	    (byte)0x40, (byte)0xf2, (byte)0x77, (byte)0x3, (byte)0x7d,
	    (byte)0x81, (byte)0x2d, (byte)0xeb, (byte)0x33, (byte)0xa0,
	    (byte)0xf4, (byte)0xa1, (byte)0x39, (byte)0x45, (byte)0xd8,
	    (byte)0x98, (byte)0xc2, (byte)0x96, (byte)0x4f, (byte)0xe3,
	    (byte)0x42, (byte)0xe2, (byte)0xfe, (byte)0x1a, (byte)0x7f,
	    (byte)0x9b, (byte)0x8e, (byte)0xe7, (byte)0xeb, (byte)0x4a,
	    (byte)0x7c, (byte)0xf, (byte)0x9e, (byte)0x16, (byte)0x2b,
	    (byte)0xce, (byte)0x33, (byte)0x57, (byte)0x6b, (byte)0x31,
	    (byte)0x5e, (byte)0xce, (byte)0xcb, (byte)0xb6, (byte)0x40,
	    (byte)0x68, (byte)0x37, (byte)0xbf, (byte)0x51, (byte)0xf5
	};

	public static void setCurveParameters(ECKey eckey) {
		eckey.setFieldFP(nistp256_p, (short)0, (short)(nistp256_p.length));
		eckey.setA(nistp256_a, (short)0, (short)(nistp256_a.length));
		eckey.setB(nistp256_b, (short)0, (short)(nistp256_b.length));
		eckey.setG(nistp256_G, (short)0, (short)(nistp256_G.length));
		eckey.setR(nistp256_R, (short)0, (short)(nistp256_R.length));
	}

	public static KeyPair newKeyPair(boolean keyEncryption) {
		final ECPrivateKey ecPriv;
		final ECPublicKey ecPub;

		ecPriv = (ECPrivateKey)KeyBuilder.buildKey(
			KeyBuilder.TYPE_EC_FP_PRIVATE,
			PRIVATE_KEY_LENGTH,
			keyEncryption
		);

		ecPub = (ECPublicKey)KeyBuilder.buildKey(
			KeyBuilder.TYPE_EC_FP_PUBLIC,
			PRIVATE_KEY_LENGTH,
			keyEncryption
		);

		ECP256.setCurveParameters(ecPriv);
		ECP256.setCurveParameters(ecPub);

		return new KeyPair(ecPub, ecPriv);
	}
}