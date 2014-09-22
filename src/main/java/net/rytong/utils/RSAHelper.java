package net.rytong.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class RSAHelper {
	private static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws NoSuchAlgorithmException {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

			StringWriter writer = new StringWriter();
			io(new InputStreamReader(ins), writer, -1);

			byte[] encodedKey = writer.toString().getBytes();

			// 先base64解码
			encodedKey = Base64.decodeBase64(encodedKey);

			return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		} catch (IOException ex) {

		} catch (InvalidKeySpecException ex) {

		}

		return null;
	}

	private static void io(Reader in, Writer out, int bufferSize) throws IOException {
		if (bufferSize == -1) {
			bufferSize = 8192 >> 1;
		}
		char[] buffer = new char[bufferSize];
		int amount;

		while ((amount = in.read(buffer)) >= 0) {
			out.write(buffer, 0, amount);
		}
	}

	/**
	 * 验签
	 * 
	 * @param content
	 *            : 需要进行验证的内容。
	 * @param sign
	 *            : 支付宝返回给商户的签名值。
	 * @param publicKey
	 *            : Rsa公钥。通过登录无线商户签约平台获取安全校验码（RSA）>>支付宝公钥。
	 * @return
	 */
	// static String publicKey =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDC+AN0WboA2YbxgfhGxyQ8XeYPTGRz/EeWnwM1xM7ZfIk+gLh1U3QhZ4lEv5wIEM8fT8+y2hmTqv7GbATX7CnaIsXwLWAdNClSpqSIeBtT6XQmX1J0PcKflZMoKLKlcqs2TIg64ZQUFuBtAf4JWuiQy/U9S75ITZKP/WPRnKTnGQIDAQAB";
	public static boolean doCheck(String content, String sign, String publicKey) {
		// public static boolean doCheck(String content, String sign) {
		try {
			PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initVerify(pubKey);
			signature.update(content.getBytes("UTF-8"));
			return signature.verify(Base64.decodeBase64(sign.getBytes()));
		} catch (Exception e) {
			return false;
		}
	}

	private static String readText(InputStream in, String encoding, int bufferSize) throws IOException {
		Reader reader = (encoding == null) ? new InputStreamReader(in) : new InputStreamReader(in, encoding);
		StringWriter writer = new StringWriter();
		io(reader, writer, bufferSize);
		return writer.toString();
	}

	/**
	 * 生成sign值
	 * 
	 * @param content
	 *            : 要签名的原始内容。
	 * @param privateKey
	 *            : Rsa签名私钥。
	 * @return
	 */
	// static String privateKey =
	// "MIICXAIBAAKBgQDC+AN0WboA2YbxgfhGxyQ8XeYPTGRz/EeWnwM1xM7ZfIk+gLh1"
	// + "U3QhZ4lEv5wIEM8fT8+y2hmTqv7GbATX7CnaIsXwLWAdNClSpqSIeBtT6XQmX1J0"
	// + "PcKflZMoKLKlcqs2TIg64ZQUFuBtAf4JWuiQy/U9S75ITZKP/WPRnKTnGQIDAQAB"
	// + "AoGAbLgiLQJPz2N9dTS1frpTgXoW7Nb7eRXn0ZzwDE6nze09FuZudbZInsv+ZqC2"
	// + "YGUHKn214xTtiEOk+H6JoOInX2seJ9v7L07rXGjBGqKreX14p8WFc9h2/oAx8Oup"
	// + "eIHidlEOXTyTuViKRqLTqFpr/c51WVSBPomuSCiugmv9EGUCQQD1T89j0H6aA9Km"
	// + "D32Fa1uTeT0/x0dcPY0dBhbNk/+FIY1Iuhr+1nNHmGjinPRM81a8MxVHasQP39S3"
	// + "PtuAXqQTAkEAy3auf7E8HDgDGB2YwpNqkyW1udCHpWdLO0nnBvGz1mLfLKPRpYYV"
	// + "54rg0PvH4RL2MYAvIpO7Tu6QGiB8Aju1owJAcHJSFWXn8n9YGsi09+h7RMFpYwfc"
	// + "3RpVyf0VjMyPbx4vHiw43aG9nA7v3RinH3LXylL8KQ12q/QlFI5anaBEQwJBALVQ"
	// + "A9zi8jArcgZxiYoLmHOcrT7zJ44saIxjJS+5AIvR/BNpwQ+CV87IoeNaHBrkrOQb"
	// + "2OU1RTvnuB6wSBItZ4UCQHfUw3/PEQf3pSaS3BPLay97Bjw/PBTmskiOef5PKYGy"
	// + "L/Kp/3vHzchRBxBwvVRvw7SKrQfFvQdSzBBR/0v6qAo=";

	public static String sign(String content, String privateKey) {
		// public static String sign(String content) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = readText(new ByteArrayInputStream(privateKey.getBytes()), "UTF-8", -1).getBytes();
			// 先base64解码
			encodedKey = Base64.decodeBase64(encodedKey);
			PrivateKey priKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));

			Signature signature = Signature.getInstance("SHA1WithRSA");

			signature.initSign(priKey);
			signature.update(content.getBytes("UTF-8"));

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (Exception e) {
			return "";
		}
	}
}
