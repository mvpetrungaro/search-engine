package app.io;

import app.model.Search;

public interface SearchInputStrategy {
	Search getSearchParamsFromInput() throws Exception;
}
