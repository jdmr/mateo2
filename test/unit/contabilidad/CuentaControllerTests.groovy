package contabilidad



import org.junit.*
import grails.test.mixin.*


@TestFor(CuentaController)
@Mock(Cuenta)
class CuentaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/cuenta/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.cuentaInstanceList.size() == 0
        assert model.cuentaInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.cuentaInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.cuentaInstance != null
        assert view == '/cuenta/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/cuenta/show/1'
        assert controller.flash.message != null
        assert Cuenta.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cuenta/list'


        def cuenta = new Cuenta()

        // TODO: populate domain properties

        assert cuenta.save() != null

        params.id = cuenta.id

        def model = controller.show()

        assert model.cuentaInstance == cuenta
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cuenta/list'


        def cuenta = new Cuenta()

        // TODO: populate valid domain properties

        assert cuenta.save() != null

        params.id = cuenta.id

        def model = controller.edit()

        assert model.cuentaInstance == cuenta
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cuenta/list'

        response.reset()


        def cuenta = new Cuenta()

        // TODO: populate valid domain properties

        assert cuenta.save() != null

        // test invalid parameters in update
        params.id = cuenta.id

        controller.update()

        assert view == "/cuenta/edit"
        assert model.cuentaInstance != null

        cuenta.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/cuenta/show/$cuenta.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/cuenta/list'

        response.reset()

        def cuenta = new Cuenta()

        // TODO: populate valid domain properties
        assert cuenta.save() != null
        assert Cuenta.count() == 1

        params.id = cuenta.id

        controller.delete()

        assert Cuenta.count() == 0
        assert Cuenta.get(cuenta.id) == null
        assert response.redirectedUrl == '/cuenta/list'


    }


}