package app.io;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import app.model.Search;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandLineSearchInputStrategy implements SearchInputStrategy {
	
	private final String[] input;

	@Override
	public Search getSearchParamsFromInput() throws InputParseException {
		
		Options options = new Options();
		
		Option rn = new Option("rn", "restaurantName", true, "restaurant name");
		options.addOption(rn);
		
		Option cr = new Option("cr", "customerRating", true, "customer rating");
		options.addOption(cr);
		
		Option d = new Option("d", "distance", true, "distance");
		options.addOption(d);
		
		Option p = new Option("p", "price", true, "price");
		options.addOption(p);
		
		Option c = new Option("c", "cuisine", true, "cuisine");
		options.addOption(c);
		
		CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        
        try {
			CommandLine cmd = parser.parse(options, input);
			
			Search.Builder searchBuilder = new Search.Builder();
			
			if (cmd.hasOption(rn)) {
				searchBuilder.byRestaurantName(cmd.getOptionValue(rn));
			}
			
			if (cmd.hasOption(cr)) {
				searchBuilder.byCustomerRating(Integer.valueOf(cmd.getOptionValue(cr)));
			}
			
			if (cmd.hasOption(d)) {
				searchBuilder.byDistance(Integer.valueOf(cmd.getOptionValue(d)));
			}
			
			if (cmd.hasOption(p)) {
				searchBuilder.byPrice(Integer.valueOf(cmd.getOptionValue(p)));
			}
			
			if (cmd.hasOption(c)) {
				searchBuilder.byCuisine(cmd.getOptionValue(c));
			}
			
			return searchBuilder.build();
		} catch (Exception e) {
			formatter.printHelp("java -jar search-engine.jar [-rn txt] [-cr num] [-d num] [-p num] [-c txt]", options);
			
			throw new InputParseException("Failed to parse input");
		}
	}
}
