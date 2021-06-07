package com.faber.airline.flight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

public class FlightSpecificationsBuilder {
	private final List<SearchCriteria> params;

    public FlightSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public FlightSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Flight> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
          .map(FlightSpecification::new)
          .collect(Collectors.toList());
        
        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result)
                  .and(specs.get(i));
        }       
        return result;
    }
}
