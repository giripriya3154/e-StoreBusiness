package cs.miu.edu.mapper;

import cs.miu.edu.domain.Transaction;
import cs.miu.edu.dto.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public TransactionDto mapToDto(Transaction transaction) {
        TransactionDto transactionDto = TransactionDto.builder()
                .orderId(transaction.getOrderId())
                .paymentMethod(transaction.getPaymentMethod())
                .transactionCode(transaction.getTransactionCode())
                .total(transaction.getTotal())
                .build();
        return transactionDto;
    }

    public Transaction mapToTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .orderId(transactionDto.getOrderId())
                .paymentMethod(transactionDto.getPaymentMethod())
                .transactionCode(transactionDto.getTransactionCode())
                .total(transactionDto.getTotal())
                .build();
        return transaction;
    }
}
