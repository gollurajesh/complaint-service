package com.complaint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@CrossOrigin
public class ComplaintApplication {

    
	public static void main(String[] args) {
		//SSLContextHelper.setSslProperties();
		SpringApplication.run(ComplaintApplication.class, args);
	}
	
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	protected static class SSLContextHelper {
		
		
		private static final String DEFAULT_SSL_CERTIFICATE = "rds-combined-ca-bundle.pem";
		private static final String SSL_CERTIFICATE = "sslCertificate";
		private static final String KEY_STORE_TYPE = "JKS";
		private static final String KEY_STORE_PROVIDER = "SUN";
		private static final String KEY_STORE_FILE_PREFIX = "rds-ca-certs";
		private static final String KEY_STORE_FILE_SUFFIX = ".jks";
		private static final String DEFAULT_KEY_STORE_PASSWORD = "changeit";
		private static final String SSL_TRUST_STORE = "javax.net.ssl.trustStore";
		private static final String SSL_TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
		private static final String SSL_TRUST_STORE_TYPE = "javax.net.ssl.trustStoreType";

		
		

		private static void setSslProperties() {

			try {
				String sslCertificate= System.getProperty(SSL_CERTIFICATE);
				if(StringUtils.isEmpty(sslCertificate)) {
					sslCertificate= DEFAULT_SSL_CERTIFICATE;
				}
				
				System.setProperty(SSL_TRUST_STORE, createKeyStoreFile(sslCertificate));
				System.setProperty(SSL_TRUST_STORE_TYPE, KEY_STORE_TYPE);
				System.setProperty(SSL_TRUST_STORE_PASSWORD, DEFAULT_KEY_STORE_PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		private static String createKeyStoreFile(String sslCertificate) throws Exception {
			return createKeyStoreFile(createCertificate(sslCertificate)).getPath();
		}

		private static X509Certificate createCertificate(String sslCertificate) throws Exception {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			URL url = new File(sslCertificate).toURI().toURL();
			if (url == null) {
				throw new Exception();
			}
			try (InputStream certInputStream = url.openStream()) {
				return (X509Certificate) certFactory.generateCertificate(certInputStream);
			}
		}

		private static File createKeyStoreFile(X509Certificate rootX509Certificate) throws Exception {
			File keyStoreFile = File.createTempFile(KEY_STORE_FILE_PREFIX, KEY_STORE_FILE_SUFFIX);
			try (FileOutputStream fos = new FileOutputStream(keyStoreFile.getPath())) {
				KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE, KEY_STORE_PROVIDER);
				ks.load(null);
				ks.setCertificateEntry("rootCaCertificate", rootX509Certificate);
				ks.store(fos, DEFAULT_KEY_STORE_PASSWORD.toCharArray());
			}
			return keyStoreFile;
		}

	}
}
