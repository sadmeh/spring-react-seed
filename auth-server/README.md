For test the server please run this request:
`curl -u clientId:secret -X POST localhost:9000/oauth/token\?grant_type=password\&username=user\&password=pass

please consider changing keystore.jks in auth server and public key in recourse server.`

**code reference:**
https://www.javacodegeeks.com/2019/03/centralized_-authorization_-oauth2_jwt.html