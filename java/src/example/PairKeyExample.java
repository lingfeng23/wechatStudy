package example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author malf
 * @description 非对称加密示例
 * @project wechatStudy
 * @since 2020/11/13
 */
public class PairKeyExample {
	public static void main(String[] args) {
		try {
			// 定义加密算法
			String algorithm = "RSA";
			String message = "张三，你好，我是李四";
			// 采用指定的算法产生张三的密钥对(keyPairZhang)
			KeyPairGenerator keyGeneratorZhang = KeyPairGenerator.getInstance(algorithm);
			// 指定密钥长度为1024位
			keyGeneratorZhang.initialize(1024);
			// 产生密钥对
			KeyPair keyPairZhang = keyGeneratorZhang.generateKeyPair();

			System.out.println("生成张三的公钥对");
			// 张三生成公钥(publicKeyZhang)并发送给李四,这里发送的是公钥的数组字节
			byte[] publicKeyZhangEncode = keyPairZhang.getPublic().getEncoded();
			// 通过网络或磁盘等方式,把公钥编码传送给李四
			// 李四接收到张三编码后的公钥,将其解码
			KeyFactory keyFactoryLi = KeyFactory.getInstance(algorithm);
			// 公钥采用X.509编码
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyZhangEncode);
			// 将公钥的KeySpec对象转换为公钥
			PublicKey publicKeyZhang = keyFactoryLi.generatePublic(x509KeySpec);
			System.out.println("李四成功解码,得到张三的公钥");

			// 李四用张三的公钥加密信息，并发送给李四
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKeyZhang);
			// 得到加密信息
			byte[] cipherMessage = cipher.doFinal(message.getBytes());
			System.out.println("加密后信息：" + new String(cipherMessage));
			System.out.println("加密完成，发送给李四...");

			// 张三用自己的私钥解密从李四处收到的信息
			cipher.init(Cipher.DECRYPT_MODE, keyPairZhang.getPrivate());
			// 得到解密后信息
			byte[] originalMessage = cipher.doFinal(cipherMessage);
			System.out.println("张三收到信息，解密后为：" + new String(originalMessage));
		} catch (Exception e) {
		}
	}
}
