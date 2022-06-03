package team2.api.mobile.gplx.service.interfaces;

import team2.api.mobile.gplx.commondata.GenericService;
import team2.api.mobile.gplx.models.TrafficSign;

public interface TrafficSignService extends GenericService<TrafficSign, String> {
	TrafficSign findTrafficSignById(String id);
	TrafficSign update(TrafficSign trafficSign, String id);

}
