package com.webservice;

import javax.jws.WebService;

import org.tempuri.ITrunkCircuitOrderService;

import net.honovi.inswitch.types.TrunkCircuit;

@WebService(targetNamespace = "http://tempuri.org/", 
portName="BasicHttpBinding_ITrunkCircuitOrderService",
serviceName="TrunkCircuitOrderService", 
endpointInterface="org.tempuri.ITrunkCircuitOrderService")
public class TrunkCircuitOrderServiceImpl implements ITrunkCircuitOrderService {

	@Override
	public String cancelTrunkCircuitOrderProjectGroup(String projectGroup) {
		// TODO Auto-generated method stub
		return "Call to cancelTrunkCircuitOrderProjectGroup Successful!";
	}

	@Override
	public String putTrunkCircuitOrder(TrunkCircuit trunkCircuitOrder) {
		// TODO Auto-generated method stub
		return "Call to cancelTrunkCircuitOrderProjectGroup Successful!";
	}

}
