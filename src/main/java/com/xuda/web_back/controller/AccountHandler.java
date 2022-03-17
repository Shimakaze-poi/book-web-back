package com.xuda.web_back.controller;

import com.xuda.web_back.entity.AccountList;
import com.xuda.web_back.repository.AccountRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.LinkedHashMap;
import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountHandler
{
    @Value("${key.privateKey}")
    private String privateKey;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/findaccount")
    public AccountList findAccount(@RequestBody LinkedHashMap<String, String> findMethod)
    {
        String needVerifyPwd = findMethod.get("accountpwd");
        AccountList returnAccount = accountRepository.findFirstByAccountname(findMethod.get("accountname"));
        if (returnAccount == null)
        {
            return null;
        }
        String outStr = null;
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(returnAccount.getAccountpwd().getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = null;
        try
        {
            priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        //RSA解密
        Cipher cipher = null;
        try
        {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        try
        {
            assert cipher != null;
            cipher.init(Cipher.DECRYPT_MODE, priKey);
        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        try
        {
            outStr = new String(cipher.doFinal(inputByte));
        } catch (IllegalBlockSizeException | BadPaddingException e)
        {
            e.printStackTrace();
        }

        return Objects.equals(outStr, needVerifyPwd) ? returnAccount : null;
    }

    @PostMapping("/findaccountname")
    public boolean findAccountName(@RequestBody LinkedHashMap<String, String> findMethod)
    {
        return accountRepository.findFirstByAccountname(findMethod.get("accountname")) != null;
    }

    @PostMapping("/add")
    public void add(@RequestBody AccountList accountList)
    {
        accountRepository.save(accountList);
    }
}
