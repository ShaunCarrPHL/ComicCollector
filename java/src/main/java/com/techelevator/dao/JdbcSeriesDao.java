package com.techelevator.dao;

import com.techelevator.model.Series;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JdbcSeriesDao implements SeriesDao{
    @Override
    public List<Series> listAllSeries() {
        return null;
    }

    @Override
    public Series getGetSeriesByName(String seriesName) {
        return null;
    }

    @Override
    public Series getSeriesById(int seriesId) {
        return null;
    }
}
