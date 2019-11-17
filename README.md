## 🥳Acmedcare-CPCDP

Acmedcare+ Cardiovascular Health Alliance Chest Pain Center Data Platform (CPCDP)

心血管健康联盟胸痛中心填报平台

### Architecture

> editing...

### Environment deployment


### Building from Source

You don’t need to build from source to use `Acmedcare+ CPCDP` (binaries in [repo.acmedcare.com](http://47.97.26.165:8081/repository/maven-public/)), 
but if you want to try out the latest and greatest, 
`Acmedcare+ CPCDP` can be easily built with the maven wrapper. You also need JDK 1.8.

*First* : git clone source from gitlab
 
```bash
$ git clone http://115.29.47.72:8082/acmedback/Acmedcare-CPCDP.git
```

*Second* : build

```bash
$ mvn clean install
```

If you want to build with the regular `mvn` command, you will need [Maven v3.5.0 or above](https://maven.apache.org/run-maven/index.html).


### Documents

*First* : add maven dependency
 
```xml

<repositories>
    <repository>
        <id>acmedcare-repo</id>
        <name>Acmedcare+ Maven Repository</name>
        <url>http://47.97.26.165:8081/repository/maven-public/</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>


<dependency>
    <groupId>com.acmedcare.framework</groupId>
    <artifactId>spring-boot-starter-cpcdp</artifactId>
    <version>2.1.0.BUILD-SNAPSHOT</version>
</dependency>

```

*Second* : config applets properties in `application.properties`

```properties


```

### Reference Document

> editing...

### License
 
```
Copyright (c) 2019 Acmedcare+

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
