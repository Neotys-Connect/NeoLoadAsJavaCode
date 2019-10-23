package com.neotys.testing.framework;



import com.neotys.neoload.model.v3.project.server.Server;
import com.neotys.neoload.model.v3.project.userpath.*;
import com.neotys.neoload.model.v3.project.variable.Variable;
import com.neotys.neoload.model.v3.util.Parameter;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hrexed on 04/07/18.
 */
public abstract class BaseNeoLoadUserPath {

	private static final AtomicInteger DELAY_COUNTER = new AtomicInteger(0);
	private static final AtomicInteger THINK_TIME_COUNTER = new AtomicInteger(0);
	public static final String HEADER="HEADER";
	public static final String BODY="BODY";
	public static final String BOTH="BOTH";
	private final UserPath virtualUser;

	public BaseNeoLoadUserPath(final BaseNeoLoadDesign design) {
		virtualUser = createVirtualUser(design);
	}

	protected static UserPath.Builder endContainer(UserPath.Builder builder,List<Step> initsteps) {
		return builder.init(Container.builder().addAllSteps(initsteps).name("init").build());
	}

	protected static UserPath.Builder initContainer(UserPath.Builder builder,List<Step> endsteps) {
		return builder.end(Container.builder().addAllSteps(endsteps).name("end").build());
	}

	protected static Container.Builder actionsContainerBuilder() {
		return Container.builder().name("Actions");
	}

	protected static String parameter(final String parameterName, final String parameterValue) {
		return parameterName+"="+parameterValue;
	}
	/*protected static Validator regexpValidator(final String validatorname, final String regexp, boolean hastocontain)
	{

		return ImmutableValidator Validator.builder()
				.name(validatorname)
				.haveToContains(hastocontain)
				.validationRegex(regexp)
				.build();
	}*/
	protected static Header header(final String headerName, final String headervalue)
	{
		return Header.builder()
				.name(headerName)
				.value(headervalue)
				.build();
	}
	protected static VariableExtractor extractor(final String variableExtractorName, final String from, final String regexp, int occurence, boolean exitonerror, Optional<String> xpath,Optional<String> jsonpath) {
		VariableExtractor.From type;
		switch(from)
		{
			case BODY:
				type=VariableExtractor.From.BODY;
				break;
			case HEADER:
				type=VariableExtractor.From.HEADER;
				break;
			default:
				type=VariableExtractor.From.BOTH;
		}
		return VariableExtractor.builder()
				.name(variableExtractorName)
				.regexp(regexp)
				.matchNumber(occurence)
				.from(type)
				.throwAssertionError(exitonerror)
				.xpath(xpath)
				.jsonPath(jsonpath)
				.build();

	}
	protected static Delay delay(final long duration) {
		return delay(duration, "delay_" + DELAY_COUNTER.incrementAndGet(), false);
	}

	protected static ThinkTime thinkTime(final long duration) {
		return createthinkTime(duration, "think_time_" + THINK_TIME_COUNTER.incrementAndGet(), true);
	}

	private static ThinkTime createthinkTime(final long duration, final String name, final boolean isThinkTime) {
		return ThinkTime.builder()
				.name(name)
				.value(String.valueOf(duration))
				.description(Optional.empty())
				.build();
	}

	private static Delay delay(final long duration, final String name, final boolean isThinkTime) {
		return Delay.builder()
				.name(name)
				.value(String.valueOf(duration))
				.build();
	}

	protected static String variabilize(final Variable var) {
		return variabilize(var.getName());
	}

	protected static String variabilize(final String variableName) {
		return "${" + variableName + "}";
	}

	protected static UserPath.Builder userPathBuilder(final String name) {
		return UserPath.builder()
				.name(name);


	}


//	protected static ImmutableRequest.Builder postFormBuilder(final Server server, final String path, final Collection<String> parameters, final Collection<String> postParameters, final Collection<VariableExtractor> extractors, final Collection<RegexpValidator> validators) {
protected static ImmutableRequest.Builder postFormBuilder(final Server server, final String path, final Collection<String> parameters, final Collection<String> postParameters, final Collection<VariableExtractor> extractors,final Optional<String> slaprofilename) {

		String body=String.join("&",postParameters);
		String uri = path;
		if(parameters!=null) {
			if(parameters.size()>0) {
				String url = String.join("&", parameters);
				uri += "?" + url;
			}
		}
		return Request.builder()
				.name(path)
				.url(uri)
				.slaProfile(slaprofilename)
				.server(server.getName())
				.method(Request.Method.POST.toString())
				.body(body)
				//.addAllValidators(validators)
				.addAllExtractors(extractors);
	}

 	protected static ImmutableRequest.Builder postFormBuilderWithHeaders(final Server server,final Collection<Header> headers, final String path, final Collection<String> parameters, final Collection<String> postParameters,final Collection<VariableExtractor> extractors,final Optional<String> slaprofilename) {

	//protected static ImmutableRequest.Builder postFormBuilderWithHeaders(final Server server,final Collection<Header> headers, final String path, final Collection<String> parameters, final Collection<String> postParameters,final Collection<VariableExtractor> extractors, final Collection<RegexpValidator> validators) {
		String uri = path;
		if(parameters!=null) {
			if(parameters.size()>0) {
				String url = String.join("&", parameters);
				uri += "?" + url;
			}
		}
		return Request.builder()
				.name(path)
				.slaProfile(slaprofilename)
				.url(uri)
				.server(server.getName())
				.method(Request.Method.POST.toString())
				.body(String.join("&",postParameters))
				.addAllExtractors(extractors)
				//.addAllValidators(validators)
				.addAllHeaders(headers);
	}

	protected static ImmutableRequest.Builder postTextBuilder(final Server server, final String path,final Collection<String> parameters, final String data, final Collection<VariableExtractor> extractors,Optional<String> slaprofilename) {

//	protected static ImmutableRequest.Builder postTextBuilder(final Server server, final String path,final Collection<String> parameters, final String data, final Collection<VariableExtractor> extractors, final Collection<RegexpValidator> validators) {
		String uri = path;
		if(parameters!=null) {
			if(parameters.size()>0) {
				String url = String.join("&", parameters);
				uri += "?" + url;
			}
		}
		return Request.builder()
				.name(path)
				.slaProfile(slaprofilename)
				.url(uri)
				.server(server.getName())
				.method(Request.Method.POST.toString())
				.body(data)
				.addAllExtractors(extractors);
				//.addAllValidators(validators);
	}

	protected static ImmutableRequest.Builder postTextBuilderWithHeaders(final Server server,final Collection<Header> headers, final String path, final Collection<String> parameters, final String data,final Collection<VariableExtractor> extractors,final Optional<String> slaprofile) {

//	protected static ImmutableRequest.Builder postTextBuilderWithHeaders(final Server server,final Collection<Header> headers, final String path, final Collection<String> parameters, final String data,final Collection<VariableExtractor> extractors, final Collection<RegexpValidator> validators) {
		String uri = path;
		if(parameters!=null) {
			if(parameters.size()>0) {
				String url = String.join("&", parameters);
				uri += "?" + url;
			}
		}
		return Request.builder()
				.name(path)
				.slaProfile(slaprofile)
				.url(uri)
				.server(server.getName())
				.method(Request.Method.POST.toString())
				.body(data)
				.addAllExtractors(extractors)
				.addAllHeaders(headers);
				//.addAllValidators(validators)

	}

	protected static ImmutableRequest.Builder getBuilder(final Server server, final String path, final Collection<String> parameters, final Collection<VariableExtractor> extractors, final Optional<String> slaprofile) {

//	protected static ImmutableRequest.Builder getBuilder(final Server server, final String path, final Collection<String> parameters, final Collection<VariableExtractor> extractors, final Collection<RegexpValidator> validators) {
		String uri = path;
		if(parameters!=null) {
			if(parameters.size()>0) {
				String url = String.join("&", parameters);
				uri += "?" + url;
			}
		}
		return Request.builder()
				.server(server.getName())
				.slaProfile(slaprofile)
				.name(path)
				.url(uri)
				.method(Request.Method.GET.toString())
				.addAllExtractors(extractors);
				//.addAllValidators(validators);
	}
	protected static ImmutableRequest.Builder getBuilderWithHeaders(final Server server,final Collection<Header> headers, final String path, final Collection<String> parameters,final Collection<VariableExtractor> extractors,final Optional<String> slaprofile) {

//		protected static ImmutableRequest.Builder getBuilderWithHeaders(final Server server,final Collection<Header> headers, final String path, final Collection<String> parameters,final Collection<VariableExtractor> extractors, final Collection<RegexpValidator> validators) {
		String uri = path;
		if(parameters!=null) {
			if(parameters.size()>0) {
				String url = String.join("&", parameters);
				uri += "?" + url;
			}
		}
		return Request.builder()
				.slaProfile(slaprofile)
				.server(server.getName())
				.name(path)
				.url(uri)
				.method(Request.Method.GET.toString())
				.addAllExtractors(extractors);
				//.addAllValidators(validators);
	}
	protected static Container container(final String name, final Collection<Step> children,Optional<String> slaprofile) {
		return Container.builder()
				.name(name)
				.slaProfile(slaprofile)
				.addAllSteps(children)
				.build();
	}

	protected static Container container(final String name,final Optional<String> slaprofile, final Step... children) {
		return Container.builder()
				.name(name)
				.slaProfile(slaprofile)
				.addSteps(children)
				.build();
	}

	public UserPath getVirtualUser() {
		return virtualUser;
	}

	public abstract UserPath createVirtualUser(final BaseNeoLoadDesign design);

	public String getName() {
		return virtualUser.getName();
	}

}
