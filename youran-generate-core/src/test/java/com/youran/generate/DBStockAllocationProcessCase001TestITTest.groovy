package com.youran.generate


import org.springframework.beans.factory.annotation.Autowired

import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.annotation.Resource

/**
 * Description
 *
 *场景：验证能够完成分配的单子
 *
 * @author yanghuai@jd.com
 * @date 2018/5/13 上午11:31
 * Project name: xstore-wms-dpallocation
 */
@ContextConfiguration(classes = AppStart.class)
@SpringBootTest
@ActiveProfiles("dev")
class DBStockAllocationProcessCase001TestITTest extends Specification {


    @Autowired
    private StockAllocationProcess stockAllocationProcess

    @Resource
    private AllocationCore groupAllocationService


    @Resource
    private CompleteDeliveryOrderService completeDeliveryOrderService

    @Resource
    private ReceiveDoService receiveDoServiceImpl

    @Resource
    private ReplDeliveryOrderService replDeliveryOrderService

    @Resource
    private WmsAlcItemServiceImpl wmsAlcItemServiceImpl

    private StkWriteRpcService stkWriteRpcService = Mock(StkWriteRpcService)

    private StockQuery stkQueryService = Mock(StockQuery)



    /**
     *     select * from alc_do where do_no='test6000568989601';
     *     select * from alc_do_item  where do_no='test6000568989601';
     *     select * from alc_stk_item where do_no='test6000568989601';
     *
     *     delete from alc_do where do_no='test6000568989601';
     *     delete from alc_do_item where do_no='test6000568989601';
     *     delete from alc_stk_item where do_no='test6000568989601';
     */
    @Transactional
    @Rollback
    def "test allocation main flow process"() {
        /**
         *1、数据库添加数据
         *
         *2、跑业务逻辑
         *
         *3、删除数据
         *
         */
        given:
            groupAllocationService.stkQueryRpcService = stkQueryService
            completeDeliveryOrderService.stkWriteRpcService = stkWriteRpcService
            replDeliveryOrderService.stkWriteRpcService = stkWriteRpcService

            Long tenantId = 99
            Long whId = 1312199999999
            String doNo = "test_"+randomNumber()
            String uuid = doNo+"-1"

            WmsAlcDoDTO wmsAlcDoDTO = new WmsAlcDoDTO(
                    tenantId:tenantId,
                    whId:whId,
                    uuid:uuid,
                    doNo:doNo,
                    doSource: 1,
                    stockOutStrategy:1,
                    allocPriority:1,
                    allocStrategy:1,
                    status:0
            )

            WmsAlcItemDTO wmsAlcItemDTO01 = new WmsAlcItemDTO(
                    tenantId:tenantId,
                    whId:whId,
                    doNo:doNo,
                    doItemNo: "1000000001",
                    status: 0,
                    locNo: 1,
                    alcItemstrategy:1,
                    wmsSkuId:1,
                    skuName:1,
                    skuId:1,
                    skuType:1,
                    saleMode:1,
                    groupId:1,
                    expectedQty:10,
                    allocQty:0,
                    expectedPickNum:1,
                    allocPickNum:1,
                    suspendReson:1,
                    supplierCode:1
            )

            WmsAlcItemDTO wmsAlcItemDTO02 = new WmsAlcItemDTO(
                    tenantId:tenantId,
                    whId:whId,
                    doNo:doNo,
                    doItemNo: "1000000002",
                    status: 0,
                    locNo: 1,
                    alcItemstrategy:1,
                    wmsSkuId:1,
                    skuName:1,
                    skuId:1,
                    skuType:1,
                    saleMode:1,
                    groupId:1,
                    expectedQty:10,
                    allocQty:0,
                    expectedPickNum:1,
                    allocPickNum:1,
                    suspendReson:1,
                    supplierCode:1
            )

            List<WmsAlcItemDTO> listWmsAlcItemDTO = new ArrayList<>()
            listWmsAlcItemDTO.add(wmsAlcItemDTO01)
            listWmsAlcItemDTO.add(wmsAlcItemDTO02)
            wmsAlcDoDTO.listWmsAlcItemDTO = listWmsAlcItemDTO

            /**库存对象(模拟查询库存得到的数据)*/
            StockItem stockEAItem01 = new StockItem(
                    whId:whId,
                    wmsSkuId:19203,
                    locNo:000001L,
                    qty:50,
                    qtyHold:10,
                    qtyAllocated:0,
                    qtyPending:0,
            )

            StockItem stockRSItem01 = new StockItem(
                    whId:whId,
                    wmsSkuId:19203,
                    locNo:000001L,
                    qty:50,
                    qtyHold:0,
                    qtyAllocated:0,
                    qtyPending:0
            )

            WmsAlcDoDO wmsAlcDoDO = new WmsAlcDoDO(
                    tenantId:tenantId,
                    whId:whId,
                    uuid:uuid,
                    doNo:doNo,
                    doSource: 1,
                    stockOutStrategy:1,
                    allocPriority:1,
                    allocStrategy:1,
                    status:0,
                    version: 1
            )

            List<StockItem> listEAStockItem = new ArrayList()
            List<StockItem> listRSStockItem = new ArrayList()
            listEAStockItem.add(stockEAItem01)
            listRSStockItem.add(stockRSItem01)

            StockInfo stockInfo = new StockInfo()
            stockInfo.listEAStockItem.addAll(listEAStockItem)
            stockInfo.listRSStockItem.addAll(listRSStockItem)
            stockInfo.setToLocNo(10000000L)
            stockInfo.setFromLocNo(20000000L)
            stockInfo.setDefaultPickLoc(true)

            receiveDoServiceImpl.receiveDoInfo(wmsAlcDoDTO)
            (1..5) * stkQueryService.queryAllListStock(_) >> stockInfo
            (0..5) * stkWriteRpcService.writeStk(_,_)>>null

        when:
            /**主体逻辑*/

            AllocationResult allocationResult = stockAllocationProcess.doAllocationStk(wmsAlcDoDO)
            List<StockOptLocItem> listStockOptLocItemxxx = allocationResult.getOptLocItemList()
        then:
            listStockOptLocItemxxx.size() == 2
            allocationResult.listNeedReplAlcItem.size() == 0
            allocationResult.listLackAlcItem.size() == 0

    }


    String randomNumber() {
        Random random = new Random(99990000)
        long randomValue = random.nextLong()
        long currentTime = System.currentTimeMillis()
        return currentTime +"_"+randomValue
    }



}

