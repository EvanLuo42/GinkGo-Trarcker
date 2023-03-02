package com.phakel.ginkgo.tracker.util;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Optional;

public class Md5Util {
    public static Optional<String> fileMd5(InputStream inputStream) {
        try {
            var md = MessageDigest.getInstance("MD5");

            var buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            inputStream.close();

            var md5Bytes  = md.digest();
            var bigInt = new BigInteger(1, md5Bytes);
            return Optional.of(bigInt.toString(16));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
