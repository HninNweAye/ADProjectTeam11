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
import sa48.team11.adproject.models.Disbursement;
import sa48.team11.adproject.models.Employee;
import sa48.team11.adproject.models.Item;
import sa48.team11.adproject.models.ItemDisburse;
import sa48.team11.adproject.models.ItemSpinner;
import sa48.team11.adproject.models.LoginModel;
import sa48.team11.adproject.models.Request;
import sa48.team11.adproject.models.RequestItem;
import sa48.team11.adproject.models.Retrieval;
import sa48.team11.adproject.models.StockCard;

public interface ApiService {

    @POST("login")
    Call<ResponseObj<Employee>> login(@Body LoginModel user);

    @GET("employees/{deptId}")
    Call<ResponseList<Employee>> getEmployeeList(@Path("deptId") String deptId);

    @GET("collectionpoints")
    Call<ResponseList<CollectionPoint>> getCollectionPoints();

    @GET("items")
    Call<ResponseList<ItemSpinner>> getItems();

    @GET("categories")
    Call<ResponseList<Category>> getCategories();

    //DepartmentHead API
    @POST("head/{headId}/delegates")
    Call<BaseResponse> delegate(@Path("headId") int headId, @Body Delegation delegation);

    @GET("head/{deptId}/delegates")
    Call<ResponseList<Delegation>> getDelegationHistory(@Path("deptId") String deptId);

    @PUT("head/{headId}/{deptId}/delegates/cancel")
    Call<BaseResponse> cancelDelegation(@Path("headId") int headId,@Path("deptId") String deptId, @Body Delegation d);

    @GET("head/dept/{deptId}/collectionpoints/representative")
    Call<ResponseObj<CollectionPointAndRep>> getCollectionPointsAndDeptRep(@Path("deptId") String deptId);

    @PUT("head/{deptId}/collectionpoints/{pointId}/representative/{repId}")
    Call<BaseResponse> updateCollectionPointAndRep(@Path("pointId") int pointId, @Path("repId") int repId, @Path("deptId") String deptId);

    @GET("head/{deptId}/requests")
    Call<ResponseList<Request>> getRequestHistory(@Path("deptId") String deptId);

    @GET("head/requests/{reqId}/detail")
    Call<ResponseList<RequestItem>> getRequestDetails(@Path("reqId") int reqId);

    @PATCH("head/requests/{reqId}")
    Call<BaseResponse> updateRequestStatus(@Body Request req);


    @PUT("representative/{deptId}/collectionpoints/{pointId}")
    Call<BaseResponse> updateCollectionPoint(@Path("pointId") int pointId,@Path("deptId") String deptId);

    @GET("representative/{deptId}/disbursements")
    Call<ResponseListAndObj<ItemDisburse, CollectionPoint>> getDisbursementInfo(@Path("deptId") String deptId);

    @GET("representative/{deptId}/disbursements/approve")
    Call<BaseResponse> approveDisbursement(@Path("deptId") String deptId);


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

    @POST("clerk/{clerkId}/retrievals")
    Call<BaseResponse> confirmRetrievals(@Path("clerkId") int clerkID,@Body List<Retrieval> retrievals);

    @GET("clerk/{clerkId}/disbursements")
    Call<ResponseList<Disbursement>> getDisbursementList(@Path("clerkId") int clerkID);

    @POST("clerk/{clerkId}/{deptId}/disbursements")
    Call<BaseResponse> updateDisbursementItems(@Path("clerkId") int clerkID,@Path("deptId") String deptID,@Body List<ItemDisburse> items);

    @GET("clerk/{clerkId}/collectionPoints")
    Call<ResponseList<CollectionPoint>> getCollectionPointByClerk(@Path("clerkId") int clerkID);

}

