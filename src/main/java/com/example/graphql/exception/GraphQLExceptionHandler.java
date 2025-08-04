package com.example.graphql.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof AuthorNotFoundException) {
            return GraphqlErrorBuilder.newError()
                    .message(ex.getMessage())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        }
        
        if (ex instanceof BookNotFoundException) {
            return GraphqlErrorBuilder.newError()
                    .message(ex.getMessage())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        }
        
        if (ex instanceof ValidationException) {
            return GraphqlErrorBuilder.newError()
                    .message(ex.getMessage())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();
        }
        
        // Para outras exceções, retorna erro genérico
        return GraphqlErrorBuilder.newError()
                .message("Internal server error")
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .errorType(ErrorType.INTERNAL_ERROR)
                .build();
    }
}