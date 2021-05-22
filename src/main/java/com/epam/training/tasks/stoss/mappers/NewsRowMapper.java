package com.epam.training.tasks.stoss.mappers;

import com.epam.training.tasks.stoss.entities.NewsItem;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class NewsRowMapper implements RowMapper<NewsItem>{
    @Override
    public NewsItem map(ResultSet resultSet) throws SQLException {
        String newsItemId = NewsItem.ID;
        Long id = resultSet.getLong(newsItemId);
        String caption = resultSet.getString(NewsItem.CAPTION);
        String text = resultSet.getString(NewsItem.TEXT);
        Date date = resultSet.getDate(NewsItem.TIME);
        Time time = resultSet.getTime(NewsItem.TIME);
        return new NewsItem(id, caption, text, date, time);
    }
}
