package com.gzz.demo.encryption;
/*
javax.crypto.Cipher 类的原理
          基于 Java 中的 javax.crypto.Cipher 类，可以实现各种算法的加密和解密功能，该类是 JCE 框架的核心。

        与所有的引擎类一样，可以通过调用 Cipher 类中的 getInstance 静态工厂方法得到 Cipher 对象：
public static Cipher getInstance(String transformation,String provider);
        1
          参数 transformation 是一个字符串，它描述了由指定输入产生输出所进行的操作或操作集合。它包含密码学算法名称，比如 DES，也可以在后面包含模式和填充方式。如果没有指定模式或填充方式，就使用特定提供者指定的默认模式或默认填充方式。

          当以流密码方式请求以块划分的 Cipher 时，可以在模式名后面跟上一次运算需要操作的 bit 数目。如果没有指定数目，则使用提供者指定的默认值。

          通过 getInstance 得到的 Cipher 对象使用下列四个模式之一进行初始化，这四个模式在 Cipher 类中被定义为 final integer 常数，可以使用符号名来引用这些模式：

        ENCRYPT_MODE, 加密数据；
        DECRYPT_MODE, 解密数据；
        WRAP_MODE, 将一个 Key 封装成字节，可以用来进行安全传输；
        UNWRAP_MODE, 将前述已封装的密钥解开成 java.security.Key 对象。
        1
        2
        3
        4
          每个 Cipher 初始化方法使用一个模式参数 opmod，并用此模式初始化 Cipher 对象。此外还有其他参数，包括密钥 key、包含密钥的证书 certificate、算法参数 params 和随机源 random。

          加密和解密必须使用相同的参数。当 Cipher 对象被初始化时，它将失去以前得到的所有状态，即初始化 Cipher 对象与新建一个 Cipher 实例后将它初始化是等价的。

        调用 doFinal（）方法完成单步的加密或解密数据：
          在多步加密或解密数据时，首先需要一次或多次调用 update 方法，用以提供加密或解密的所有数据。

          如果还有输入数据，多步操作可以使用 doFinal 方法之一结束。如果没有数据，多步操作可以使用 doFinal 方法结束。

          如果在 transformation 参数部分指定了 padding 或 unpadding 方式，则所有的 doFinal 方法都要注意所用的 padding 或 unpadding 方式。

          调用 doFinal 方法将会重置 Cipher 对象到使用 init 进行初始化时的状态，也就是说，Cipher 对象被重置，使得可以进行更多数据的加密或解密。这两种模式可以在调用 init 时进行指定。

        3、wrap 密钥必须先使用 WRAP_MODE 初始化 Cipher 对象，然后调用方法：

public final byte[] wrap(Key key);
        1
          如果将调用 wrap 方法后的密钥字节提供给 unwrap 的人使用，必须向接收者发送额外信息。

          （1）密钥算法名称：

          调用 Key 接口提供的 getAlgorithm 方法：

public String getAlgorithm();
        1
          （2）包裹密钥的类型：

        （Cipher.SECRET_KEY,Cipher.PRIVATE_KEY,Cipher.PUBLIC_KEY)
        1
        SunJCE 提供者实现的 Cipher 算法参数：
          （1）采用 CBC、CFB、OFB、PCBC 模式的 DES、DES-EDE 和 Blowfish算法使用初始化向量 I V IVIV 作为参数。可以使用 javax.crypto.spec.IvParameterSpec 类并使用给定的 I V IVIV 参数来初始化 Cipher 对象。

          （2）PBEWithMD5AndDES 使用的参数是一个由盐值和迭代次数组成的参数集合。可以使用 javax.crypto.spec.PBEParameterSpec 类并利用给定盐值和迭代次数来初始化 Cipher 对象。

          （3）Cipher 中的某些 update 和 doFinal 方法允许调用者指定加密或解密数据的输出缓存。此时，保证指定的缓存足够大以容纳加密或解密运算的结果是非常重要的，可以使用 Cipher 的以下方法来决定输出缓存应该有多大：

public int getOutputSize(int inputLen)。
*/
/*
* 对称算法：
  密钥管理：比较难，不适合互联网，一般用于内部系统；
  安全性：中；
  速度：快好几个数量级(软件加解密速度至少快 100 倍，每秒可以加解密几兆比特的数据)，适合大数据量的加解密处理。

非对称算法：
  密钥管理：密钥容易管理；
  安全性：高；
  速度：慢，适合小数据量加解密或数据签名。

Hash 算法：
  MD5 输出 128 bit、SHA1 输出 160 bit、SHA256 输出 256 bit。

  SHA-1 是 160 位的哈希值，而 SHA-2 是组合值，有不同的位数，其中最受欢迎的是 256 位。

  因为 SHA-2 有多种不同的位数，导致这个名词有一些混乱。但无论是“SHA-2”、“SHA-256” 或 “SHA-256 位”，其实都是同一种加密算法。SHA-224、SHA-384 或 SHA-512 表示 SHA-2 的二进制长度。

  SSL 行业选择 SHA 作为数字签名的散列算法，但随着互联网技术的提升，SHA-1 的缺点越来越突显。在 SHA-2 成为了新的标准之后，签发的 SSL 证书必须使用该算法签名。

  安全性方面，SHA-256 的安全性最高，但是耗时要比其他两种多很多。MD5 相对较容易破解，因此，SHA-1 是这三种中性能较好的一款哈希算法。
————————————————
版权声明：本文为CSDN博主「carol980206」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/carol980206/article/details/96705859
*
* */
