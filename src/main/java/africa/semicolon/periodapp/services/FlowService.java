package africa.semicolon.periodapp.services;

import africa.semicolon.periodapp.dto.response.FlowOverview;

import java.util.List;

public interface FlowService {

    List<FlowOverview> getOverviews(Integer length);

}
