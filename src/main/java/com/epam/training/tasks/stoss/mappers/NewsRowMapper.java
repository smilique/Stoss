package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.NewsItem;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import static com.epam.training.tasks.stoss.entities.NewsItem.*;

public class NewsRowMapper implements RowMapper<NewsItem>{
    @Override
    public NewsItem map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String caption = resultSet.getString(CAPTION);
        String text = resultSet.getString(TEXT);
        Date date = resultSet.getDate(TIME);
        Time time = resultSet.getTime(TIME);
        return new NewsItem(id, caption, text, date, time);
    }
}
