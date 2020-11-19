package com.lalee.madlevel6task2.movieApiService

import com.google.gson.*
import java.lang.reflect.Type


internal class InterfaceAdapter<T> : JsonSerializer<T>, JsonDeserializer<T> {
    override fun serialize(
        objectt: T,
        interfaceType: Type?,
        context: JsonSerializationContext
    ): JsonElement {
        val wrapper = JsonObject()
        wrapper.addProperty("type", objectt.toString())
        wrapper.add("data", context.serialize(objectt))
        return wrapper
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        elem: JsonElement,
        interfaceType: Type?,
        context: JsonDeserializationContext
    ): T {
        val wrapper = elem as JsonObject
        val typeName = get(wrapper, "type")
        val data = get(wrapper, "data")
        val actualType: Type = typeForName(typeName)
        return context.deserialize(data, actualType)
    }

    private fun typeForName(typeElem: JsonElement): Type {
        return try {
            Class.forName(typeElem.asString)
        } catch (e: ClassNotFoundException) {
            throw JsonParseException(e)
        }
    }

    private operator fun get(wrapper: JsonObject, memberName: String): JsonElement {
        return wrapper[memberName]
            ?: throw JsonParseException("no '$memberName' member found in what was expected to be an interface wrapper")
    }
}