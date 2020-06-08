package webservices;


import domain.Word;
import domain.Wordlist;
import persistence.WordsDao;
import persistence.WordsDaoImpl;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.process.internal.RequestContext;

import java.util.ArrayList;

@Path("/words")
public class WordsResource {
	private WordsDao wordDao = new WordsDaoImpl();

	@GET
	@Produces("application/json")
	public Response getWordLists() {
		ArrayList<Wordlist> wordLists = wordDao.getWordLists();
		return Response.ok(wordLists).build();
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response postWordLists(String jsonData) {
		Boolean result = wordDao.postWords(jsonData);
		return Response.ok(result).build();
	}
	
	
	@DELETE
	@Path("/{wordListId}")
	@Produces("application/json")
	public Response deleteWordList(@PathParam("wordListId") int id) {
		Boolean result = wordDao.deleteWordList(id);
		return Response.ok(result).build();
	}
	
	@PUT
	@Path("/{wordListId}/{newword}")
	@Produces("application/json")
	public Response putWordList(@PathParam("wordListId") int id, @PathParam("newword") String newword) {
		Boolean result = wordDao.addWord(id, newword);
		return Response.ok(result).build();
	}
	
	@GET
	@Path("/{wordListId}")
	@Produces("application/json")
	public Response getWordsFromList(@PathParam("wordListId") int id) {
		ArrayList<Word> words = wordDao.getWordsFromList(id);
		return Response.ok(words).build();
	}
}
