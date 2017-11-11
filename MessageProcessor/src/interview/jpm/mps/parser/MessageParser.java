package interview.jpm.mps.parser;

public interface MessageParser<MESSAGE> {
	
	public MESSAGE parse(String messageToBeParsed) throws Exception;

}
