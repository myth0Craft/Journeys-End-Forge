#version 150

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

uniform float GameTime;
uniform int EndPortalLayers;

in vec4 texProj0;

out vec4 fragColor;

const vec3 COLORS[16] = vec3[](
    vec3(0.022, 0.098, 0.110), vec3(0.011, 0.095, 0.089), vec3(0.027, 0.101, 0.100),
    vec3(0.046, 0.109, 0.114), vec3(0.064, 0.117, 0.097), vec3(0.063, 0.086, 0.123),
    vec3(0.084, 0.111, 0.166), vec3(0.097, 0.154, 0.091), vec3(0.106, 0.131, 0.195),
    vec3(0.097, 0.110, 0.187), vec3(0.133, 0.138, 0.148), vec3(0.070, 0.243, 0.235),
    vec3(0.196, 0.142, 0.214), vec3(0.047, 0.315, 0.321), vec3(0.204, 0.390, 0.302),
    vec3(0.080, 0.314, 0.661)
);

const mat4 SCALE_TRANSLATE = mat4(
    0.5, 0.0, 0.0, 0.25,
    0.0, 0.5, 0.0, 0.25,
    0.0, 0.0, 1.0, 0.0,
    0.0, 0.0, 0.0, 1.0
);

mat2 mat2_rotate_z(float angle) {
    float s = sin(angle);
    float c = cos(angle);
    return mat2(c, -s, s, c);
}

mat4 end_portal_layer(float layer) {
    mat4 translate = mat4(
        1.0, 0.0, 0.0, 17.0 / layer,
        0.0, 1.0, 0.0, (2.0 + layer / 1.5) * (GameTime * 1.5),
        0.0, 0.0, 1.0, 0.0,
        0.0, 0.0, 0.0, 1.0
    );

    mat2 rotate = mat2_rotate_z(radians((layer * layer * 4321.0 + layer * 9.0) * 2.0));
    mat2 scale = mat2((4.5 - layer / 4.0) * 2.0);

    mat4 m = mat4(
        scale[0][0] * rotate[0][0], scale[0][0] * rotate[0][1], 0.0, 0.0,
        scale[1][0] * rotate[1][0], scale[1][0] * rotate[1][1], 0.0, 0.0,
        0.0, 0.0, 1.0, 0.0,
        0.0, 0.0, 0.0, 1.0
    );

    return m * translate * SCALE_TRANSLATE;
}

void main() {
    vec3 color = textureProj(Sampler0, texProj0).rgb * COLORS[0];
    for (int i = 0; i < EndPortalLayers; i++) {
        color += textureProj(Sampler1, texProj0 * end_portal_layer(float(i + 1))).rgb * COLORS[i];
    }
    fragColor = vec4(color, 1.0);
}
