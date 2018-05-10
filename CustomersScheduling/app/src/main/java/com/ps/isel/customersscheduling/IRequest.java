package com.ps.isel.customersscheduling;

import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.java.dto.BusinessDto;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Colapso on 08/05/18.
 */

public interface IRequest<T>
{
    void doRequest(int typeRequest, String uri, Consumer<T> cons, Function<R,T> parser );

}
