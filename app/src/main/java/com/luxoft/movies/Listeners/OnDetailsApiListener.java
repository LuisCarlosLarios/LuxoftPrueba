package com.luxoft.movies.Listeners;

import com.luxoft.movies.models.DetailApiResponse;

public interface OnDetailsApiListener {
    void onResponse(DetailApiResponse response);
    void onError (String message);
}
