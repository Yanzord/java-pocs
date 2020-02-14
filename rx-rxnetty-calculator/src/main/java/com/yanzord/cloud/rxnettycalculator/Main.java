package com.yanzord.cloud.rxnettycalculator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yanzord.cloud.rxnettycalculator.config.CalculatorModule;
import com.yanzord.cloud.rxnettycalculator.service.Calculator;

import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.Karyon;
import netflix.karyon.KaryonBootstrapModule;
import netflix.karyon.ShutdownModule;
import netflix.karyon.archaius.ArchaiusBootstrapModule;
import netflix.karyon.health.HealthCheckHandler;
import netflix.karyon.servo.KaryonServoModule;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;

public class Main {
	public static void main(String[] args) {        
		Injector injector = Guice.createInjector(new CalculatorModule());

		HealthCheckHandler healthCheckHandler = new HealthcheckResource();
		Karyon.forRequestHandler(8080,
				new RxNettyHandler("/healthcheck", new HealthCheckEndpoint(healthCheckHandler), injector.getInstance(Calculator.class)),
				new KaryonBootstrapModule(healthCheckHandler),
				new ArchaiusBootstrapModule("calculator"),
				Karyon.toBootstrapModule(KaryonWebAdminModule.class),
				ShutdownModule.asBootstrapModule(),
				KaryonServoModule.asBootstrapModule()
				).startAndWaitTillShutdown();
	}
}