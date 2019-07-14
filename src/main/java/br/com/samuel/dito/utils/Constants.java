package br.com.samuel.dito.utils;

public class Constants {

	public final static String EVENT_BUY_PRODUCT = "comprou-produto";

	public interface INFO_EVENT {
		public final String TRANSACTION_ID = "transaction_id";
		public final String STORE_NAME = "store_name";
		public final String PRODUCT_NAME = "product_name";
		public final String PRODUCT_PRICE = "product_price";
	}
}
