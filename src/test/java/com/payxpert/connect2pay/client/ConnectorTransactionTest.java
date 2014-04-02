package com.payxpert.connect2pay.client;

import com.payxpert.connect2pay.client.Connect2payClient;

public abstract class ConnectorTransactionTest {
  protected static final String TEST_URL = "http://127.0.0.1:9001";
  protected static final String DEFAULT_ORIGINATOR = "102019";
  protected static final String DEFAULT_PASSWORD = "525c563011420f4d7a230ea1fc2fbe024031febb34da9f1508d564c5c72e0284";

  protected Connect2payClient connector;
}
