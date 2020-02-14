package com.yanzord.cloud.rxnettycalculator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yanzord.cloud.rxnettycalculator.exception.InvalidNumberException;
import com.yanzord.cloud.rxnettycalculator.exception.InvalidOperationException;
import com.yanzord.cloud.rxnettycalculator.service.Calculator;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import rx.Observable;

public class RxNettyHandler implements RequestHandler<ByteBuf, ByteBuf> {

	private final String healthCheckUri;
	private final HealthCheckEndpoint healthCheckEndpoint;
	private static final String URI_PARAMS_ERROR = "{\"Error\":\"Please provide all three params to calculate. The URI should be /calculator/{value}/{operation}/{value}\"}";
	private static final Integer FIRST_PARAM_POSITION = 0;
	private static final Integer SECOND_PARAM_POSITION = 1;
	private static final Integer THIRD_PARAM_POSITION = 2;
	private Calculator calculator;
	private Gson gson = new GsonBuilder().create();

	public RxNettyHandler(String healthCheckUri, HealthCheckEndpoint healthCheckEndpoint, Calculator calculator) {
		this.healthCheckUri = healthCheckUri;
		this.healthCheckEndpoint = healthCheckEndpoint;
		this.calculator = calculator;
	}

	@Override
	public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
		String uri = request.getUri();

		if (request.getUri().startsWith(healthCheckUri)) {
			return healthCheckEndpoint.handle(request, response);
		}

		if (uri.startsWith("/calculator/")) {
			if (uri.equals("/calculator/logs")) {
				String logs = gson.toJson(calculator.getLogs());
				return response.writeStringAndFlush(logs);
			}

			int prefixLength = "/calculator/".length();

			String[] uriParams = uri.substring(prefixLength).split("/", 0);

			if (uriParams.length != 3) {
				response.setStatus(HttpResponseStatus.BAD_REQUEST);
				return response.writeStringAndFlush(URI_PARAMS_ERROR);
			}

			try {
				Double firstValue = Double.valueOf(uriParams[FIRST_PARAM_POSITION]);
				Double secondValue = Double.valueOf(uriParams[THIRD_PARAM_POSITION]);
				String operation = uriParams[SECOND_PARAM_POSITION];

				Double result = calculator.doOperation(firstValue, operation, secondValue);

				return response.writeStringAndFlush("{\"Result\":\"" + result + "\"}");
			} catch (NumberFormatException | InvalidNumberException | InvalidOperationException e) {
				response.setStatus(HttpResponseStatus.BAD_REQUEST);
				return response.writeStringAndFlush("{\"Error\":\"" + e.toString() + "\"}");
			}
		} else {
			response.setStatus(HttpResponseStatus.NOT_FOUND);
			return response.close();
		}
	}
}