package com.poc.apigateway.config;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final ErrorMessagesConfig errorMessagesConfig;

    public static class Config {
    }

    public AuthorizationHeaderFilter(ErrorMessagesConfig errorMessagesConfig) {
        super(Config.class);
        this.errorMessagesConfig = errorMessagesConfig;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info("Processing request in AuthorizationHeaderFilter for URI: {}", request.getURI());

            if (validateAuthorizationHeader(request)) {
                log.info("Valid token found, proceeding with the filter chain");
                return chain.filter(exchange);
            } else {
                log.warn("Invalid or missing token, handling request as unauthorized");
                return handleInvalidToken().filter(exchange, chain);
            }
        };
    }

    private boolean validateAuthorizationHeader(ServerHttpRequest request) {
        if (request.getHeaders().containsKey(Constants.HEADER_AUTHORIZACION_KEY)) {
            try {
                String authToken = request.getHeaders().getFirst(Constants.HEADER_AUTHORIZACION_KEY);
                if (authToken != null) {
                    return isTokenValid(authToken);
                }
            } catch (Exception e) {
                log.error("Error validating token: ", e);
            }
        }
        return false;
    }

    private boolean isTokenValid(String authToken) {
        Date expiration = Jwts.parser()
                .setSigningKey(Constants.SECRET_KEY)
                .parseClaimsJws(authToken.replace(Constants.TOKEN_BEARER_PREFIX, ""))
                .getBody()
                .getExpiration();
        return !expiration.before(new Date());
    }

    private GatewayFilter handleInvalidToken() {
        return (exchange, chain) -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

            String errorMessage = String.format("{\"error\":\"%s\", \"message\":\"%s\"}",
                    this.errorMessagesConfig.getUnauthorized().getCode(),
                    this.errorMessagesConfig.getUnauthorized().getMessage());

            DataBuffer buffer = response.bufferFactory().wrap(errorMessage.getBytes(StandardCharsets.UTF_8));

            return response.writeWith(Mono.just(buffer));
        };
    }
}
