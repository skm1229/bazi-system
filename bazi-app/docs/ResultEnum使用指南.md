# ResultEnum 使用指南

## 概述

ResultEnum 是八字排盘系统的统一状态码枚举，提供了完整的HTTP状态码体系和业务特定错误码。

## 状态码分类

### 1. 成功状态 (2xx)
```java
ResultEnum.SUCCESS(200, "操作成功")
ResultEnum.CREATED(201, "创建成功")
ResultEnum.UPDATED(200, "更新成功")
ResultEnum.DELETED(200, "删除成功")
```

### 2. 客户端错误 (4xx)
```java
ResultEnum.PARAM_ERROR(400, "参数错误")
ResultEnum.UNAUTHORIZED(401, "未登录或登录已过期")
ResultEnum.FORBIDDEN(403, "权限不足")
ResultEnum.DATA_NOT_FOUND(404, "数据不存在")
ResultEnum.USERNAME_EXISTS(409, "用户名已存在")
ResultEnum.VALIDATION_ERROR(422, "数据验证失败")
```

### 3. 服务器错误 (5xx)
```java
ResultEnum.SYSTEM_ERROR(500, "系统繁忙，请稍后重试")
ResultEnum.DATABASE_ERROR(500, "数据库操作失败")
ResultEnum.NETWORK_ERROR(500, "网络连接失败")
```

### 4. 业务错误 (6xx)
```java
ResultEnum.BAZI_ERROR(601, "八字排盘失败")
ResultEnum.QIMEN_ERROR(602, "奇门遁甲排盘失败")
ResultEnum.ZIWEI_ERROR(603, "紫微斗数排盘失败")
ResultEnum.LIUYAO_ERROR(604, "六爻排盘失败")
ResultEnum.MEIHUA_ERROR(605, "梅花易数排盘失败")
```

## 在控制器中使用

### 1. 成功响应
```java
@PostMapping("/save")
public Result<BaziRecord> saveBaziRecord(@Valid @RequestBody BaZiDto dto) {
    BaziRecord record = baziRecordService.save(dto);
    return Result.result(ResultEnum.CREATED, "记录创建成功", record);
}

@PutMapping("/{id}")
public Result<BaziRecord> updateBaziRecord(@PathVariable Long id, @Valid @RequestBody BaZiDto dto) {
    BaziRecord record = baziRecordService.update(id, dto);
    return Result.result(ResultEnum.UPDATED, "记录更新成功", record);
}

@DeleteMapping("/{id}")
public Result<String> deleteBaziRecord(@PathVariable Long id) {
    baziRecordService.delete(id);
    return Result.result(ResultEnum.DELETED, "删除成功");
}
```

### 2. 错误响应
```java
@GetMapping("/{id}")
public Result<BaziRecord> getBaziRecord(@PathVariable Long id) {
    BaziRecord record = baziRecordService.findById(id);
    if (record == null) {
        return Result.result(ResultEnum.DATA_NOT_FOUND, "记录不存在");
    }
    return Result.result(ResultEnum.SUCCESS, "查询成功", record);
}
```

## 在异常处理中使用

### 1. 业务异常
```java
// 使用静态工厂方法
throw BusinessException.recordNotFound();
throw BusinessException.userNotFound();
throw BusinessException.baziError();

// 使用构造方法
throw new BusinessException(ResultEnum.DATA_NOT_FOUND, "八字记录不存在");
throw new BusinessException(ResultEnum.BAZI_ERROR, "八字排盘计算失败");
```

### 2. 全局异常处理
```java
@ExceptionHandler(BusinessException.class)
public Result<String> handleBusinessException(BusinessException ex) {
    return Result.result(ex.getResultEnum());
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public Result<String> handleValidationException(MethodArgumentNotValidException ex) {
    String errorMsg = extractErrorMessage(ex);
    return Result.result(ResultEnum.VALIDATION_ERROR, errorMsg);
}
```

## 在Service中使用

### 1. 数据验证
```java
@Service
public class BaziRecordServiceImpl implements BaziRecordService {
    
    @Override
    public BaziRecord getBaziRecordById(Long id, Long userId) {
        BaziRecord record = baziRecordMapper.selectOne(
            new LambdaQueryWrapper<BaziRecord>()
                .eq(BaziRecord::getId, id)
                .eq(BaziRecord::getUserId, userId)
        );
        
        if (record == null) {
            throw BusinessException.recordNotFound();
        }
        
        return record;
    }
    
    @Override
    public BaziRecord saveBaziRecord(Long userId, BaZiDto dto, BaZiVo vo) {
        try {
            // 排盘逻辑
            BaziRecord record = new BaziRecord();
            // ... 设置属性
            baziRecordMapper.insert(record);
            return record;
        } catch (Exception e) {
            throw BusinessException.baziError();
        }
    }
}
```

## 实用工具方法

### 1. 状态判断
```java
ResultEnum result = ResultEnum.SUCCESS;

if (result.isSuccess()) {
    // 处理成功情况
}

if (result.isClientError()) {
    // 处理客户端错误
}

if (result.isServerError()) {
    // 处理服务器错误
}

if (result.isBusinessError()) {
    // 处理业务错误
}
```

### 2. 查找方法
```java
// 根据状态码查找
ResultEnum result = ResultEnum.getByCode(404);

// 根据消息查找
ResultEnum result = ResultEnum.getByMsg("数据不存在");
```

## 前端处理示例

### JavaScript
```javascript
// 根据状态码处理
function handleResponse(response) {
    const { code, msg, data } = response;
    
    if (code >= 200 && code < 300) {
        // 成功处理
        showSuccess(msg);
        return data;
    } else if (code >= 400 && code < 500) {
        // 客户端错误
        showError(msg);
    } else if (code >= 500 && code < 600) {
        // 服务器错误
        showError('服务器错误，请稍后重试');
    } else if (code >= 600) {
        // 业务错误
        showWarning(msg);
    }
}
```

## 最佳实践

1. **统一使用ResultEnum**：所有API响应都应该使用ResultEnum中定义的状态码
2. **合理选择状态码**：根据实际情况选择最合适的状态码
3. **使用静态工厂方法**：优先使用BusinessException的静态工厂方法
4. **保持消息一致性**：相同类型的错误使用相同的错误消息
5. **记录适当日志**：业务错误使用WARN级别，系统错误使用ERROR级别
