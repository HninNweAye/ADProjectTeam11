package sa48.team11.adproject.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sa48.team11.adproject.models.AdjItem;
import sa48.team11.adproject.models.AdjVoucher;
import sa48.team11.adproject.models.Category;
import sa48.team11.adproject.models.CollectionPoint;
import sa48.team11.adproject.models.CollectionPointAndRep;
import sa48.team11.adproject.models.Delegation;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.Item;
import sa48.team11.adproject.models.ItemSpinner;
import sa48.team11.adproject.models.Request;
import sa48.team11.adproject.models.RequestItem;
import sa48.team11.adproject.models.Retrieval;
import sa48.team11.adproject.models.StockCard;

public interface ApiService {

    @GET("employees/{deptId}")
    Call<ResponseList<Employee>> getEmployeeList(@Path("deptId") String deptId);

    @GET("collectionpoints")
    Call<ResponseList<CollectionPoint>> getCollectionPoints();

    @GET("items")
    Call<ResponseList<ItemSpinner>> getItems();

    @GET("categories")
    Call<ResponseList<Category>> getCategories();

    //DepartmentHead API
    @POST("departments/delegate")
    Call<BaseResponse> delegate(@Body Delegation delegation);

    @GET("departments/delegates/{deptId}")
    Call<ResponseList<Delegation>> getDelegationHistory(@Path("deptId") String deptId);

    @PUT("departments/delegates/cancel/{delegationId}")
    Call<BaseResponse> cancelDelegation(@Path("delegationId") int delegationId);

    @GET("departments/collectionpoints/representative")
    Call<ResponseListAndObj<CollectionPoint, CollectionPointAndRep>> getCollectionPointsAndDeptRep();

    @PUT("departments/collectionpoints/{pointId}/representative/{repId}")
    Call<BaseResponse> updateCollectionPointAndRep(@Path("pointId") int pointId, @Path("repId") int repId);

    @GET("departments/requests")
    Call<ResponseList<Request>> getRequestHistory();

    @GET("departments/requests/{reqId}/detail")
    Call<ResponseList<RequestItem>> getRequestDetails(@Path("reqId") String reqId);

    @PATCH("departments/requests/{reqId}")
    Call<BaseResponse> updateRequestStatus(@Path("reqId") String reqId,@Body Request req);


    //StoreClerk API
    @GET("clerk/stockcard/item/{itemCode}")
    Call<ResponseListAndObj<StockCard, Item>> getStockCard(@Path("itemCode") String itemCode);

    @GET("clerk/{clerkId}/adjustmentvoucher")
    Call<ResponseList<AdjVoucher>> getAdjVoucherHistory(@Path("clerkId") int empId);

    @GET("clerk/adjustmentvoucher/{voucherId}/detail")
    Call<ResponseList<AdjItem>> getAdjVoucherItems(@Path("voucherId") int voucherId);

    @POST("clerk/{clerkId}/adjustmentvoucher")
    Call<BaseResponse> createAdjVoucher(@Path("clerkId") int clerkId, @Body List<AdjItem> items);

    @GET("clerk/retrievals")
    Call<ResponseList<Retrieval>> getRetrievalList();



}

