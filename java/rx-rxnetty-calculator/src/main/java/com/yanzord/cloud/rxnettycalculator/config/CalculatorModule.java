package com.yanzord.cloud.rxnettycalculator.config;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.yanzord.cloud.rxnettycalculator.operation.Division;
import com.yanzord.cloud.rxnettycalculator.operation.Multiply;
import com.yanzord.cloud.rxnettycalculator.operation.Operation;
import com.yanzord.cloud.rxnettycalculator.operation.Pow;
import com.yanzord.cloud.rxnettycalculator.operation.Sub;
import com.yanzord.cloud.rxnettycalculator.operation.Sum;

public class CalculatorModule extends AbstractModule {

	@Override
	protected void configure() {
		MapBinder<String, Operation> mapBinder =
				MapBinder.newMapBinder(binder(), String.class, Operation.class);

		mapBinder.addBinding("sum").to(Sum.class);
		mapBinder.addBinding("sub").to(Sub.class);
		mapBinder.addBinding("mult").to(Multiply.class);
		mapBinder.addBinding("div").to(Division.class);
		mapBinder.addBinding("pow").to(Pow.class);
	}
}
