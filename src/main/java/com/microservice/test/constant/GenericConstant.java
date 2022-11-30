package com.microservice.test.constant;

public final class GenericConstant {
    public static final String ENTITY_ACCOUNT = "account";
    public static final String ENTITY_ACCOUNTTYPE = "accountType";
    public static final String ENTITY_CUSTOMER = "customer";
    public static final String ENTITY_PERSON = "person";
    public static final String ENTITY_TRANSACTION = "transaction";
    public static final String ENTITY_TRANSACTIONTYPE = "transactionType";
    public static final String DEFAULT_STRING = "";
    public static final int DEFAULT_INTEGER = 0;
    public static final String DEFAULT_NUMERIC_BOOLEAN_TYPE = "org.hibernate.type.NumericBooleanType";
    public static final String TRASACTION_TYPE_DEBIT = "Débito";
    public static final String TRASACTION_TYPE_CREDIT = "Crédito";
    public static final String ACCOUNT_TYPE_CURRENT = "Corriente";
    public static final String TRASACTION_TYPE_SAVING = "Ahorro";
    public static final Boolean ACTIVE_STATE = Boolean.TRUE;
    public static final Boolean INACTIVE_STATE = Boolean.FALSE;
    public static final String FEMALE_GENDER = "F";
    public static final String MALE_GENDER = "M";
    public static final String MESSAGE_NOT_NULL = "No se permiten datos vacios.";
    public static final String MESSAGE_NOT_PERSON_SAVED = "No se han guardado correctamente los datos de la persona.";
    public static final String MESSAGE_NOT_CUSTOMER_SAVED = "No se han guardado correctamente los datos del cliente.";
    public static final String MESSAGE_NOT_ACCOUNTTYPE_SAVED = "No se han guardado correctamente los datos del tipo de cuenta.";
    public static final String MESSAGE_NOT_TRANSACTION_SAVED = "No se han guardado correctamente los datos de la transacción.";
    public static final String MESSAGE_CUSTOMER_SAVED = "Se han guardado correctamente los datos del cliente.";
    public static final String MESSAGE_ACCOUNT_SAVED = "Se han guardado correctamente los datos de la cuenta.";
    public static final String MESSAGE_NOT_ACCOUNT_SAVED = "Se han guardado correctamente los datos de la cuenta.";
    public static final String MESSAGE_ACCOUNTTYPE_SAVED = "Se han guardado correctamente los datos del tipo de cuenta.";
    public static final String MESSAGE_NOT_EXISTS_TRANSACTION_TYPE = "No existe el tipo de transacción.";
    public static final String MESSAGE_NOT_EXISTS_ACCOUNT = "No existe la cuenta.";
    public static final String MESSAGE_NOT_EXISTS_TRANSACTION = "No existe la transacción.";
    public static final String MESSAGE_NOT_EXISTS_ACCOUNTTYPE = "No existe el tipo de cuenta o no se ha dado de alta.";
    public static final String MESSAGE_NOT_EXISTS_CUSTOMER = "No existe el cliente o no se ha dado de alta.";
    public static final String MESSAGE_EXISTS_CUSTOMER = "Información del cliente obtenida.";
    public static final String MESSAGE_EXISTS_ACCOUNT = "Información de la cuenta obtenida.";
    public static final String MESSAGE_EXISTS_ACCOUNTTYPE = "Información del tipo de cuenta obtenida.";
    public static final String MESSAGE_CUSTOMER_ELIMINATED = "El cliente se eliminó con éxito.";
    public static final String MESSAGE_ACCOUNT_ELIMINATED = "La cuenta se eliminó con éxito.";
    public static final String MESSAGE_TRANSACTION_ELIMINATED = "La transacción se eliminó con éxito.";
    public static final String VALIDATE_ELEMENT_CUSTOMER = "Cliente";
    public static final String VALIDATE_ELEMENT_ACCOUNT = "Cuenta";
    public static final String VALIDATE_ELEMENT_SERVER = "Servidor";
    public static final String VALIDATE_ELEMENT_NOT_FOUND = "Elemento no encontrado.";
    public static final String VALIDATE_ELEMENT_TRANSACTION = "Transacción";
    public static final String MESSAGE_CUSTOMER_REQUIRED = "El cliente es requerido.";
    public static final String MESSAGE_CUSTOMER_MIN_VALUE = "El valor mínimo del cliente es {value}.";
    public static final String MESSAGE_ACCOUNT_REQUIRED = "La cuenta es requerida.";
    public static final String MESSAGE_ESTATE_REQUIRED = "El estado es requerido.";
    public static final String MESSAGE_ACCOUNT_MIN_VALUE = "El valor mínimo del cliente es {value}.";

    public static final String MESSAGE_TRANSACTION_MIN_VALUE = "El valor mínimo de la cuenta es {value}.";
    public static final String MESSAGE_INITIAL_BALANCE_REQUIRED = "El saldo inicial es requerido.";
    public static final String MESSAGE_CURRENT_BALANCE_REQUIRED = "El saldo inicial es requerido.";
    public static final String MESSAGE_ACCOUNTYPE_REQUIRED = "El tipo de cuenta es requerido.";

    public static final String MESSAGE_TRANSACTION_REQUIRED = "La transacción es requerida.";
}
