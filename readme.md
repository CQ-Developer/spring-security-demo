# Spring Security In Action

for more details, please check other branches.

```text
# windows系统若执行失败, 可以在命令前加 winpty
# 生成HTTPS证书, 可以直接在git-bash中运行
openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365
 
# 转换到PKCS12格式
# Public Key Cryptography Standards #12
openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"
```
