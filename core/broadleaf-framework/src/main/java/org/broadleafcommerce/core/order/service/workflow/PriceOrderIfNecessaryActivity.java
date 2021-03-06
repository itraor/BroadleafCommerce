/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.core.order.service.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.OrderService;
import org.broadleafcommerce.core.workflow.BaseActivity;
import org.broadleafcommerce.core.workflow.ProcessContext;

import javax.annotation.Resource;

public class PriceOrderIfNecessaryActivity extends BaseActivity {
    private static Log LOG = LogFactory.getLog(PriceOrderIfNecessaryActivity.class);
    
    @Resource(name = "blOrderService")
    protected OrderService orderService;

    public ProcessContext execute(ProcessContext context) throws Exception {
        CartOperationRequest request = ((CartOperationContext) context).getSeedData();
        Order order = request.getOrder();
        
        order = orderService.save(order, request.isPriceOrder());
        request.setOrder(order);
        
        return context;
    }

}
