# JCroft

The new AG DSN management system

[![Build Status](https://travis-ci.org/JGDSN/jcroft.svg?branch=master)](https://travis-ci.org/JGDSN/jcroft)
[![Waffle.io - Columns and their card count](https://badge.waffle.io/JGDSN/jcroft.svg?columns=all)](https://waffle.io/JGDSN/jcroft)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=de.agdsn%3Ajcroft&metric=ncloc)](https://sonarcloud.io/dashboard/index/de.agdsn%3Ajcroft) 
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=de.agdsn%3Ajcroft&metric=alert_status)](https://sonarcloud.io/dashboard/index/de.agdsn%3Ajcroft) 
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=de.agdsn%3Ajcroft&metric=coverage)](https://sonarcloud.io/dashboard/index/de.agdsn%3Ajcroft) 
[![Technical Debt Rating](https://sonarcloud.io/api/project_badges/measure?project=de.agdsn%3Ajcroft&metric=sqale_index)](https://sonarcloud.io/dashboard/index/de.agdsn%3Ajcroft) 
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=de.agdsn%3Ajcroft&metric=code_smells)](https://sonarcloud.io/dashboard/index/de.agdsn%3Ajcroft) 
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=de.agdsn%3Ajcroft&metric=bugs)](https://sonarcloud.io/dashboard/index/de.agdsn%3Ajcroft) 
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=de.agdsn%3Ajcroft&metric=vulnerabilities)](https://sonarcloud.io/dashboard/index/de.agdsn%3Ajcroft) 
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=de.agdsn%3Ajcroft&metric=security_rating)](https://sonarcloud.io/dashboard/index/de.agdsn%3Ajcroft) 
[![codecov](https://codecov.io/gh/JGDSN/jcroft/branch/master/graph/badge.svg)](https://codecov.io/gh/JGDSN/jcroft)

[![Sonarcloud](https://sonarcloud.io/api/project_badges/quality_gate?project=de.agdsn%3Ajcroft)](https://sonarcloud.io/dashboard/index/de.agdsn%3Ajcroft)

## Requirements

  - Java 8+ (plan: **Java 10**)
  - Maven
  - PostGreSQL
  - [Hazelcast.org](http://hazelcast.org) for scaling and high availability
  - LDAP server for authentification

## How To

```bash
mvn clean install
```

German introduction to maven:

  - http://jukusoft.com/2016/10/24/tutorial-eine-einfuehrung-in-maven-teil-1/
  - http://jukusoft.com/2016/10/29/tutorial-einfuehrung-maven-teil-2/
  
**Additionally you need a LDAP server.**\
For AGDSN you have to be in internal management network or add a port forwarding for **127.0.0.1:389 --> IDM Server:389**

You can add port a forwarding with [Bitvise]() for example:
![Bitvise Configuration](./docs/images/bitvise.png)

Change your jcroft config (will be created on first run) to use your tunnel to the LDAP authentication database.

**settings/jcroft.cfg**:
```text
ldap_host=localhost
ldap_port=389

jdbc_ip=localhost
jdbc_port=5432
jdbc_user=jcroft
jdbc_password=ENTER YOUR PASSWORD HERE
jdbc_database=jcroft

```

## Technical Requirements

## Non-Technical Requirements

  - failover node
  - **Privacy by design** (DSGVO-conform)
  
## Presentation

  - [Slides](https://slides.com/juku/deck)
  - [Database Schema](https://app.sqldbm.com/MySQL/Share/Hm_yVRXiAIDjSQ1FbBmW8EGFrngIE8md_DYjF4jNYw0)