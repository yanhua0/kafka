namespace java com.thrift.eth
//交易所_创建eth账号
struct EXCHANGE_CREATE_ETH_ACCOUNT_MODEL{
    1: string path, //账号存放地址
    2: string password,//密码
    3: string phone_filename//账号名称
}
//交易所_返回创建eth账号信息
struct EXCHANGE_RETURN_ETH_ACCOUNT{
    1: string address;//账号地址
    2: string privateKey;//私钥
    3: string publicKey;//公钥
    4: string password;//密码
    5: double coinQuantity;//货币数量
    6: string message;//消息
    7: i32 status;//状态
}
//交易所_转账eth
struct EXCHANGE_TRANSFER_ETH_MODEL{
    1: string https_web3j,//web3j url
    2: string path_file,//账号文件路径
    3: string address,//账号地址
    4: string password,//密码
    5: string to_address,//转到那个账号地址
    6: double quantity//转账数量
}
//交易所_返回转账信息
struct EXCHANGE_RETURN_TRANSFER{
    1: string message;//消息
    2: i32 status;//状态
}
//交易所_转账sheep
struct EXCHANGE_TRANSFER_SHEEP_MODEL{
    1: string https_web3j,//web3j url
    2: string path_file,//账号文件路径
    3: string address,//账号地址
    4: string password,//密码
    5: string to_address,//转到那个账号地址
    6: double quantity,//转账数量
    7: string contract_address//合约地址
}
service ETH_CORE{//===========================================exhagne eth相关操作
    //创建eth账号
    EXCHANGE_RETURN_ETH_ACCOUNT create_eth_address(1:EXCHANGE_CREATE_ETH_ACCOUNT_MODEL create_eth_model)
    //eth转账
    EXCHANGE_RETURN_TRANSFER transfer_eth(1:EXCHANGE_TRANSFER_ETH_MODEL transfer_eth_model)
    //获取eth余额
    double get_eth_balance(1:string https_web3j,2:string address)
    //sheep转账
    EXCHANGE_RETURN_TRANSFER transfer_sheep(1:EXCHANGE_TRANSFER_SHEEP_MODEL transfer_sheep_model)
    //获取sheep余额
    double get_sheep_balance(1:string https_web3j,2:string address,3:string contract_address)
}