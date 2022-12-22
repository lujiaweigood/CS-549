package edu.stevens.cs549.dhts.resource;

import java.net.URI;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import javax.ws.rs.DELETE;
import edu.stevens.cs549.dhts.activity.DHTBase.Invalid;
@Path("/dht")
public class NodeResource {

	/*
	 * Web service API.
	 * 
	 *  TODO: Fill in the missing operations.
	 */

	Logger log = Logger.getLogger(NodeResource.class.getCanonicalName());

	@Context
	UriInfo uriInfo;

	@Context
	HttpHeaders headers;

	@GET
	@Path("info")
	@Produces("application/json")
	public Response getNodeInfo() {
		return new NodeService(headers, uriInfo).getNodeInfo();
	}

//	@GET
//	@Path("info")
//	@Produces("application/xml")
//	public Response getNodeInfoXML() {
//		return new NodeService(headers, uriInfo).getNodeInfo();
//	}


	@GET
	@Path("pred")
	@Produces("application/json")
	public Response getPred() {
		return new NodeService(headers, uriInfo).getPred();
	}


	@PUT
	@Path("notify")
	@Consumes("application/json")
	@Produces("application/json")
	/*
	 * Actually returns a TableRep 
	 */
	public Response putNotify(TableRep predDb) {

		/*
		 * See the comment for WebClient::notify (the client side of this logic).
		 */
		return new NodeService(headers, uriInfo).notify(predDb);
	}


	@GET
	@Path("find")
	@Produces("application/json")
	public Response findSuccessor(@QueryParam("id") String index) {
		int id = Integer.parseInt(index);
		return new NodeService(headers, uriInfo).findSuccessor(id);
	}

	@GET
	@Path("succ")
	@Produces("application/json")
	public Response getSucc() {
		return new NodeService(headers, uriInfo).getSucc();
	}

	@GET
	@Path("finger")
	@Produces("application/json")
	public Response findClosestPrecedingFinger(@QueryParam("id") String index) {
		int id = Integer.parseInt(index);
		return new NodeService(headers, uriInfo).findClosestPrecedingFinger(id);
	}

	@GET
//	@Path("getValue")
	@Produces("application/json")
	public Response getValue(@QueryParam("key") String key) throws Invalid {
		return new NodeService(headers, uriInfo).getValue(key);
	}

//	@PUT
//	@Path("add")
//	@Consumes("application/xml")
//	public Response add(TableRep tablerep) throws Invalid {
//		return new NodeService(headers, uriInfo).add(tablerep.entry[0].key, tablerep.entry[0].vals[0]);
//	}

	@PUT
	/*@Consumes("application/xml")*/
	@Produces("application/json")
	@Consumes("text/plain")
	public Response addKeyValue(@QueryParam("key") String key, @QueryParam("val") String value) throws Invalid {
		return new NodeService(headers,uriInfo).addKeyValue(key,value);
	}

	@DELETE
//	@Path("delete")
//	@Consumes("application/xml")
//	MediaType.TEXT_PLAIN_TYPE
	@Produces("application/json")
	public Response delete(@QueryParam("key") String key, @QueryParam("val") String value) throws Invalid {
		return new NodeService(headers, uriInfo).delete(key, value);
	}

}
